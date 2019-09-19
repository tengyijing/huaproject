package cn.huacao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.huacao.common.pojo.EasyUiTreeNode;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.mapper.TbItemCatMapper;
import cn.huacao.pojo.TbItemCat;
import cn.huacao.pojo.TbItemCatExample;
import cn.huacao.pojo.TbItemCatExample.Criteria;
import cn.huacao.service.ItemCatService;


@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public List<EasyUiTreeNode> getItemCatList(long parentId) {
		//根据parentId查询分类
		TbItemCatExample example = new TbItemCatExample();
		//创建条件添加对象
		Criteria criteria = example.createCriteria();
		//添加条件
		criteria.andParentIdEqualTo(parentId);
		//执行返回数据
		List<TbItemCat> itemCatlist = itemCatMapper.selectByExample(example);
		//创建返回Tree节点集
		List<EasyUiTreeNode> list = new ArrayList<>();
		//循环遍历itemCatlist创建EasyUiTreeNode
		for(TbItemCat itemCat : itemCatlist) {
			EasyUiTreeNode treeNode = new EasyUiTreeNode();
			//添加id
			treeNode.setId(itemCat.getId());
			//添加text
			treeNode.setText(itemCat.getName());
			//判断state
			treeNode.setState(itemCat.getIsParent()?"closed":"open");
			list.add(treeNode);
		}
		return list;
	}

	//添加商品分类
	@Override
	public HuaCaoResult addItemCat(long parentId, String name) {
		//创建一个分类对象
		TbItemCat itemCat = new TbItemCat();
		//添加属性
		itemCat.setName(name);//'分类名称'
		itemCat.setParentId(parentId);//'父类目ID=0时，代表的是一级的类目'
		//补全属性
		Date date =new Date();
		itemCat.setCreated(date);//'创建时间'
		itemCat.setUpdated(date);//'修改时间'
		itemCat.setIsParent(false);//'该类目是否为父类目，1为true，0为false'
		itemCat.setSortOrder(1);//'排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数'
		itemCat.setStatus(1);//'状态。可选值:1(正常)
		//插入数据 会自动返回id
		itemCatMapper.insert(itemCat);
		//判断父节点原来是否是父类目
		TbItemCat parent = itemCatMapper.selectByPrimaryKey(parentId);
		if(!parent.getIsParent()) {//如果不是父类目 修改为父类目
			parent.setIsParent(true);
			//修改
			itemCatMapper.updateByPrimaryKey(parent);
		}
		return HuaCaoResult.ok(itemCat);
	}
	
	//修改商品分类
	@Override
	public HuaCaoResult updateItemCatName(TbItemCat itemCat) {
		itemCatMapper.updateByPrimaryKeySelective(itemCat);
		return HuaCaoResult.ok();
	}
	
	//修改分类级别
	@Override
	public HuaCaoResult updateItemCatIsParent(long id) {
		//查询分类是否有子目录
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//添加条件
		criteria.andParentIdEqualTo(id);
		//查询出子节点
		List<TbItemCat> sonList = itemCatMapper.selectByExample(example);
		if(sonList.isEmpty()) {//沒有子节点
			//修改目录级别
			TbItemCat itemCat = new TbItemCat();
			itemCat.setId(id);
			itemCat.setIsParent(false);
			itemCatMapper.updateByPrimaryKeySelective(itemCat);
		}
		return HuaCaoResult.ok();
	}

	//删除分类
	@Override
	public void deleteItemCat(long id) {
		//根据ID查询分类
		 TbItemCat itemCat = itemCatMapper.selectByPrimaryKey(id);
		 //如果该分类是父目录,查询出子节点删除
		 if(itemCat.getIsParent()) {
			 TbItemCatExample example = new TbItemCatExample();
			 Criteria criteria = example.createCriteria();
			 //添加条件
			 criteria.andParentIdEqualTo(id);
			 //查询出子节点
			 List<TbItemCat> sonList = itemCatMapper.selectByExample(example);
			 //循环子节点
			 for(TbItemCat TbItemCat :sonList) {
				 //递归删除
				 this.deleteItemCat(TbItemCat.getId());
			 }
		 }
		 //删除分类
		 itemCatMapper.deleteByPrimaryKey(id);
		
	}
}
