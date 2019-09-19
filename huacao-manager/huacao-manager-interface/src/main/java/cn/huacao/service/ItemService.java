package cn.huacao.service;

import java.util.List;

import cn.huacao.common.pojo.EasyUiDataGridResult;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.pojo.TbItem;

public interface ItemService {
	//根据商品ID查询商品
	public TbItem fundItemById(long itemId);
	//查询商品并分页
	public EasyUiDataGridResult fundItemList(int page, int rows);
	//添加商品
	public HuaCaoResult addItem(TbItem item , String desc);
	//修改商品
	public HuaCaoResult updateItem(TbItem item , String desc);
	//删除商品
	public HuaCaoResult updateStatus(List<Long> itemIds , byte status);
}
