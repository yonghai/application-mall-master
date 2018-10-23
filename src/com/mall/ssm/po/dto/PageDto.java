package com.mall.ssm.po.dto;

import java.io.Serializable;

/**
 * 分页参数分装
 * @author Administrator
 *
 */
public class PageDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**当前页**/
	private int page;
	/**每页显示总记录数**/
	private int limit;

	/**每页的开始记录数**/
	private int start;

    protected long totalCount = -1L;

    protected long totalSize = 0;

	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setStart(int start) {
		this.start = start;
	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public int getStart() {
		this.start = (page-1)*limit;
		return start;
	}
    public long getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }
    public long getTotalSize() {
        if (this.totalCount < 0L) {
            return -1L;
        }

        long count = this.totalCount / this.limit;
        if (this.totalCount % this.limit > 0L) {
            count += 1L;
        }
        return count;
    }
    public Integer getId() {
		return 0;
	}
}
