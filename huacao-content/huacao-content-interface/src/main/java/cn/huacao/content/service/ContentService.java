package cn.huacao.content.service;

import java.util.List;

import cn.huacao.common.pojo.EasyUiDataGridResult;
import cn.huacao.common.pojo.HuaCaoResult;
import cn.huacao.pojo.TbContent;
/**
 * 广告内容管理接口
 */
public interface ContentService {
	//根据分类查询广告内容(分页)
	public EasyUiDataGridResult fundContentBycateGoryId(int page, int rows,Long categoryId);
	//增加广告内容
	public HuaCaoResult addContent(TbContent content);
	//修改广告内容
	public HuaCaoResult updateContent(TbContent content);
	//删除广告内容
	public HuaCaoResult deleteContentByid(List<Long> ids);
	//根据分类查询广告内容(前台展示)
	public List<TbContent> fundContent(Long categoryId);
}
