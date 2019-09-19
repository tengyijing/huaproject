package cn.huacao.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huacao.common.pojo.EasyUiTreeNode;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.content.service.ContentCategoryService;
import cn.huacao.mapper.TbContentCategoryMapper;
import cn.huacao.pojo.TbContentCategory;
import cn.huacao.pojo.TbContentCategoryExample;
import cn.huacao.pojo.TbContentCategoryExample.Criteria;



/**
 * 内容分类管理业务层
 * @author Administrator
 *
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMpper;
	
	//根据父类ID查询内容分类
	@Override
	public List<EasyUiTreeNode> fundContentCategory(long parentId) {
		//添加查询条件
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//查询
		List<TbContentCategory> contentCategorylist = contentCategoryMpper.selectByExample(example );
		//建立返回值集合
		List<EasyUiTreeNode> list = new ArrayList<>();
		//遍历查询的结果创建EasyUiTreeNode树形节点
		for(TbContentCategory contentCategory : contentCategorylist) {
			EasyUiTreeNode easyUiTreeNode = new EasyUiTreeNode();
			//设置id
			easyUiTreeNode.setId(contentCategory.getId());
			//设置内容
			easyUiTreeNode.setText(contentCategory.getName());
			//树形结构的状态 closed表示还有子节点，open表示没有子节点
			easyUiTreeNode.setState(contentCategory.getIsParent()?"closed":"open");
			//添加到返回值集合
			list.add(easyUiTreeNode);
		}
		return list;
	}
	
	//添加内容分类
	@Override
	public HuaCaoResult addContentCategory(long parentId, String name) {
		//创建一个分类对象
		TbContentCategory contentCategory = new TbContentCategory();
		//添加属性
		contentCategory.setName(name);//'分类名称'
		contentCategory.setParentId(parentId);//'父类目ID=0时，代表的是一级的类目'
		//补全属性
		Date date =new Date();
		contentCategory.setCreated(date);//'创建时间'
		contentCategory.setUpdated(date);//'修改时间'
		contentCategory.setIsParent(false);//'该类目是否为父类目，1为true，0为false'
		contentCategory.setSortOrder(1);//'排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数'
		contentCategory.setStatus(1);//'状态。可选值:1(正常)
		//插入数据 会自动返回id
		contentCategoryMpper.insert(contentCategory);
		//判断父节点原来是否是父类目
		TbContentCategory parent = contentCategoryMpper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()) {//如果不是父类目 修改为父类目
			parent.setIsParent(true);
			//修改
			contentCategoryMpper.updateByPrimaryKey(parent);
		}
		return HuaCaoResult.ok(contentCategory);
	}

	//修改分类名
	@Override
	public HuaCaoResult updateContentCategoryName(TbContentCategory contentCategory) {
		contentCategoryMpper.updateByPrimaryKeySelective(contentCategory);
		return HuaCaoResult.ok();
	}
	
	//删除分类
	@Override
	public void deleteContentCategory(long id) {
		 //根据ID查询分类
		 TbContentCategory contentCategory = contentCategoryMpper.selectByPrimaryKey(id);
		 //如果该分类是父目录,查询出子节点删除
		 if(contentCategory.getIsParent()) {
			 TbContentCategoryExample example = new TbContentCategoryExample();
			 Criteria criteria = example.createCriteria();
			 //添加条件
			 criteria.andParentIdEqualTo(id);
			 //查询出子节点
			 List<TbContentCategory> sonList = contentCategoryMpper.selectByExample(example);
			 //循环子节点
			 for(TbContentCategory sonContentCategory :sonList) {
				 //递归删除
				 this.deleteContentCategory(sonContentCategory.getId());
			 }
		 }
		 //删除分类
		 contentCategoryMpper.deleteByPrimaryKey(id);
	}
	
	//修改分类目录级别
	@Override
	public HuaCaoResult updateContentCategoryIsParent(long id) {
		//查询分类是否有子目录
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		//添加条件
		criteria.andParentIdEqualTo(id);
		//查询出子节点
		List<TbContentCategory> sonList = contentCategoryMpper.selectByExample(example);
		if(sonList.isEmpty()) {//沒有子节点
			//修改目录级别
			TbContentCategory contentCategory = new TbContentCategory();
			contentCategory.setId(id);
			contentCategory.setIsParent(false);
			contentCategoryMpper.updateByPrimaryKeySelective(contentCategory);
		}
		return HuaCaoResult.ok();
	}
}
