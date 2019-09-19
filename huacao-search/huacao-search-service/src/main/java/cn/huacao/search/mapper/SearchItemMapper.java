package cn.huacao.search.mapper;

import java.util.List;

import cn.huacao.common.pojo.SearchItem;

public interface SearchItemMapper {
	//查询所有的商品
	public List<SearchItem> getItemList();
	public SearchItem getItemByid(long itemId);
}
