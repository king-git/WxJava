package me.chanjar.weixin.cp.api.impl;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpExternalContactService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactInfo;
import me.chanjar.weixin.cp.bean.WxCpUserExternalContactList;
import me.chanjar.weixin.cp.bean.WxCpUserWithExternalPermission;

import java.util.List;

public class WxCpExternalContactServiceImpl implements WxCpExternalContactService {
  private WxCpService mainService;

  public WxCpExternalContactServiceImpl(WxCpService mainService) {
    this.mainService = mainService;
  }

  @Override
  public WxCpUserExternalContactInfo getExternalContact(String userId) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/crm/get_external_contact?external_userid=" + userId;
    String responseContent = this.mainService.get(url, null);
    return WxCpUserExternalContactInfo.fromJson(responseContent);
  }

  @Override
  public List<String> listExternalContacts(String userId) throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/list?userid=" + userId;
    String responseContent = this.mainService.get(url, null);
    WxCpUserExternalContactList list = WxCpUserExternalContactList.fromJson(responseContent);
    return list.getExternalUserId();
  }

  @Override
  public List<String> listFollowUser() throws WxErrorException {
    String url = "https://qyapi.weixin.qq.com/cgi-bin/externalcontact/get_follow_user_list";
    String responseContent = this.mainService.get(url, null);
    WxCpUserWithExternalPermission list = WxCpUserWithExternalPermission.fromJson(responseContent);
    return list.getFollowUser();
  }
}
