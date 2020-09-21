export const settingConfig = {
  default: [
    {
      name: "accountInformation",
      isEnable: true,
      text: "账号信息",
      position: "top",
      sort: "100",
    },
    {
      name: "mobilePhoneNumber",
      isEnable: true,
      text: "手机号码",
      position: "top",
      sort: "200",
    },
    {
      name: "theme",
      isEnable: true,
      text: "主题",
      position: "top",
      sort: "300",
    },
    {
      name: "layoutStyle",
      isEnable: true,
      text: "风格",
      position: "top",
      sort: "400",
      viewName: "app-mob-select-changeStyle",
    },
    {
      name: "notification",
      isEnable: true,
      text: "消息通知",
      position: "center",
      sort: "100",
    },
    {
      name: "privacy",
      isEnable: true,
      text: "隐私",
      position: "center",
      sort: "200",
    },
    {
      name: "universal",
      isEnable: true,
      text: "通用",
      position: "center",
      sort: "300",
    },
    {
      name: "accessibility",
      isEnable: true,
      text: "辅助功能",
      position: "center",
      sort: "400",
    },
    {
      name: "about",
      isEnable: true,
      text: "关于",
      position: "center",
      sort: "500",
    },
    {
      name: "logout",
      isEnable: true,
      text: "退出登录",
      position: "bottom",
      sort: "100",
    },
    {
      name: "clear",
      isEnable: true,
      text: "清除缓存",
      position: "bottom",
      sort: "200",
    },
  ],
  // 个人中心视图（需用户手动配置）
  userCenterViewName: "user-user-center-mob-edit-view",
  // 用户实体参数名称
  userEntityName: "user",
  // 自定义功能
  userCustomize: [
    //   {
    // 名称（不可重复）
    //     name: "test",
    // 是否启用
    //     isEnable: true,
    // 显示文本
    //     text: "test",
    //     // 板块位置（内置为三个板块，分别为 top、center、bottom）
    //     position: "center",
    //     // 板块排序（与其他item的排序位置）
    //     sort: "201",
    // 打开视图名称
    //     viewName: "",
    //   },
  ],
};
