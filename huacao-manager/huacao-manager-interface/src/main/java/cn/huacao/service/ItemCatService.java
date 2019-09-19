package cn.huacao.service;

import java.util.List;

import cn.huacao.common.pojo.EasyUiTreeNode;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.pojo.TbItemCat;


/**
 *商品分类管理
 */
public interface ItemCatService {
	public List<EasyUiTreeNode> getItemCatList(long parentId);
	//添加商品分类
	public HuaCaoResult addItemCat(long parentId,String name);
	//修改商品分类名
	public HuaCaoResult updateItemCatName(TbItemCat itemCat);
	//修改商品分类目录级别
	public HuaCaoResult updateItemCatIsParent(long id);
	//删除商品分类
	public void deleteItemCat(long id);
}
