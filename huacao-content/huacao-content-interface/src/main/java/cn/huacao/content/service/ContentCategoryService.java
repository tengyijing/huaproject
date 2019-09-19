package cn.huacao.content.service;

import java.util.List;

import cn.huacao.common.pojo.EasyUiTreeNode;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.pojo.TbContentCategory;

/**
 * 内容分类管理借口
 * @author Administrator
 *
 */
public interface ContentCategoryService {
	//查询内容分类
	public List<EasyUiTreeNode> fundContentCategory(long parentId);
	//添加内容分类
	public HuaCaoResult addContentCategory(long parentId,String name);
	//修改内容分类名
	public HuaCaoResult updateContentCategoryName(TbContentCategory contentCategory);
	//修改分类目录级别
	public HuaCaoResult updateContentCategoryIsParent(long id);
	//删除分类
	public void deleteContentCategory(long id);
} 
