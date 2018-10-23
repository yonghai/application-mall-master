package com.mall.ssm.mapper.wx;

import java.util.List;

import com.mall.ssm.base.BaseDao;
import com.mall.ssm.po.WxUserLoginRecord;
import com.mall.ssm.po.custom.WxUserCustom;
import com.mall.ssm.po.vo.WxUserLoginRecordVo;
import com.mall.ssm.po.vo.WxUserVo;

/**
 * 微信用户操作
 * @author Administrator
 *
 */
public interface WxUserMapper extends BaseDao<WxUserVo,WxUserCustom>{
	public void insertLoginRecord(WxUserLoginRecordVo loginRecordVo);
	public List<WxUserLoginRecord> getLoginRecodeList(String dateStr);
	public List<WxUserCustom> getNewUsers(String dateStr);
}
