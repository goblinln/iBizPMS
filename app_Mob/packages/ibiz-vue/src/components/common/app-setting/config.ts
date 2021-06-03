export const settingConfig = {
  default: [
    {
      name: "userheader",
      isEnable: true,
      text: "userheader",
      position: "top",
      sort: "99",
      entext:'userheader',
      component :'app-user-header',
    },
    {
      name: "theme",
      isEnable: true,
      text: "主题",
      position: "center",
      sort: "300",
      entext:'Theme',
    },
    {
      name: "layoutStyle",
      isEnable: true,
      text: "风格",
      position: "center",
      sort: "400",
      viewName: "app-mob-select-changeStyle",
      entext:'Style',
    },
    // {
    //   name: "notification",
    //   isEnable: true,
    //   text: "消息通知",
    //   position: "center",
    //   sort: "100",
    //   entext:'Notification',
    // },
    // {
    //   name: "privacy",
    //   isEnable: true,
    //   text: "隐私",
    //   position: "center",
    //   sort: "200",
    //   entext:'Privacy',
    // },
    // {
    //   name: "universal",
    //   isEnable: true,
    //   text: "通用",
    //   position: "center",
    //   sort: "300",
    //   entext:'Universal',
    // }, 
    {
      name: "accessibility",
      isEnable: true,
      text: "辅助功能",
      position: "center",
      sort: "400",
      entext:'Accessibility',
    },
    {
      name: "about",
      isEnable: true,
      text: "关于",
      position: "center",
      sort: "500",
      viewName: "",
      entext:'About',
    },
    {
      name: "clear",
      isEnable: true,
      text: "清除缓存",
      position: "bottom",
      sort: "100",
      entext:'Clear cache',
    },
    {
      name: "language",
      isEnable: true,
      text: "切换语言",
      position: "bottom",
      sort: "200",
      entext:'Switch language',
    },
    {
      name: "logout",
      isEnable: true,
      text: "退出登录",
      position: "end",
      sort: "100",
      entext:'Sign out',
    },    
  ],
  // 个人中心视图（需用户手动配置）
  userCenterViewName: "sys-employee-login-mob-edit-view",
  // 用户实体参数名称
  userEntityName: "user",
  // 自定义功能
  userCustomize: [
      {
    // 名称（不可重复）
        name: "Update log",
        entext:'Update log',
    // 是否启用
        isEnable: true,
    // 显示文本
        text: "更新日志",
        // 板块位置（内置为三个板块，分别为 top、center、bottom）
        position: "center",
        // 板块排序（与其他item的排序位置）
        sort: "201",
        //路径
        path:"sysupdatelogs/views/mobmdview"
      },
  ],
};
