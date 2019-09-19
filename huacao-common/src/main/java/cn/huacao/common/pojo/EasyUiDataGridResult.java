package cn.huacao.common.pojo;

import java.io.Serializable;
import java.util.List;


/**
 * 分页数据返回结果
 * @author Administrator
 *
 */
public class EasyUiDataGridResult implements Serializable {

	private static final long serialVersionUID = 1L;
	//数据总数
	private Long total;
	//分页的数据
	private List<?> rows;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
