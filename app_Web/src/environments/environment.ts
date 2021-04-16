


export const Environment = {
    // 原型示例数模式
    SampleMode: false,
    // 应用名称
    AppName: 'Web',
    // 应用 title
    AppTitle: 'iBiz软件生产管理',
    // 应用基础路径
    BaseUrl: '',
    // 系统名称
    SysName: 'iBizPMS',
    // 远程登录地址，本地开发调试使用
    RemoteLogin: '/ibizutil/login',
    // 文件导出
    ExportFile: '/ibizutil/download',
    // 文件上传
    UploadFile: '/ibizutil/upload',
    // 数据导入单次上传最大数量
    sliceUploadCnt: 100,
    // 是否为pc端应用
    isAppMode:true,
    //统一地址
    uniteAddress:"http://172.16.100.202:8114",
    // 是否为开发模式
    devMode: true,
    // 是否开启权限认证
    enablePermissionValid:false,
    // 菜单权限模式，可选值：RT(RT模式),RESOURCE(资源模式),MINIX(混合模式),默认MINIX
    menuPermissionMode:"MINIX",
    // 项目模板地址
    ProjectUrl: "http://demo.ibizlab.cn/ibizr7pfstdtempl/ibizvuer7",
    // 打开目标工具，可选参数：sln、mos
    debugOpenMode:'mos',
    // 配置平台地址
    StudioUrl: "http://172.16.170.145/mos/",
    // 中心标识
    SlnId: "B4BF5C84-D020-4D9A-A986-8FA4FD72816C",
    // 系统标识
    SysId: "B428B5BE-EA90-4101-A493-BA7085D89F0A",
    // 前端应用标识
    AppId: "6e0b7357169ef4eba84e1347ed94bd84",
    // 项目发布文件地址
    PublishProjectUrl: 'https://oauth2:0fc5b56b52f5ad87efd3850b6536e034@gitee.com/ibizlab/iBizPMS.git',
    // ibiz开放平台地址
    ibizlabtUrl: 'https://www.ibizlab.cn',
    // ibiz论坛地址
    ibizbbstUrl: 'https://bbs.ibizlab.cn',
    // 应用动态路径
    appDynaModelFilePath: "PSSYSAPPS/Web/PSSYSAPP.json",
    // 远端动态基础路径
    remoteDynaPath: "",
    // 远端动态副本路径
    remoteDynaPath2: "",
    // 预览动态基础路径
    previewDynaPath: "http://172.16.170.145",
    // 实例配置地址
    configDynaPath:"http://172.16.170.145/DynamicBackend/designtool/redirect",
    // 是否开启工作流
    workflow: false,
    // 是否启用动态
    bDynamic: false,
    // 动态模式(RT/WEB)
    dynaMode:"WEB",
    // 是否预览模式
    isPreviewMode: false,
    // SaaS模式
    SaaSMode: true,
    // 钉钉内部集成应用标识，用于钉钉应用内免登
    dingTalkAppId: "",
    // 钉钉登录应用标识，用于网页扫码登录
    dingTalkAccAppId:"",
    // 企业微信登录应用标识,用于网页扫码登录和企业微信内部免登
    wxWorkAppId:"",
    // 登录地址
    loginUrl:""
};
// 挂载外部配置文件
if ((window as any).Environment) {
    Object.assign(Environment, (window as any).Environment);
}