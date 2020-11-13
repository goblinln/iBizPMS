export const Environment = {
    // 原型示例数模式
    SampleMode: false,
    // 应用名称
    AppName: 'Mob',
    // 应用 title
    AppTitle: 'iBiz软件生产管理（Mob）',
    // 应用基础路径
    BaseUrl: '../',
    // 系统名称
    SysName: 'pms',
    // 远程登录地址，本地开发调试使用
    // RemoteLogin: 'ibizutil/login',
    RemoteLogin: '/v7/login',
    // 文件导出
    ExportFile: 'ibizutil/download',
    // 文件上传
    UploadFile: 'ibizutil/upload',
    // 是否为pc端应用
    isAppMode: true,
    // 是否开启权限认证
    enablePermissionValid: false,
    //统一地址
    uniteAddress: 'http://172.16.100.202:8114',
    // 是否为开发模式
    devMode: true,
    // 项目模板地址
    ProjectUrl: 'http://172.16.180.229/wangxiang1/VUE_R7_FTL',
    // 配置平台地址
    StudioUrl: 'http://172.16.170.145/slnstudio/',
    // 中心标识
    SlnId: 'B4BF5C84-D020-4D9A-A986-8FA4FD72816C',
    // 系统标识
    SysId: 'B428B5BE-EA90-4101-A493-BA7085D89F0A',
    // 前端应用标识
    AppId: '6e0b7357169ef4eba84e1347ed94bd84',
    // 项目发布文件地址
    PublishProjectUrl: 'https://oauth2:0fc5b56b52f5ad87efd3850b6536e034@gitee.com/ibizlab/iBizPMS.git',
    // ibiz开放平台地址
    ibizlabtUrl: 'https://www.ibizlab.cn',
    // ibiz论坛地址
    ibizbbstUrl: 'https://bbs.ibizlab.cn',
    // 是否开启访客模式
    VisitorsMode: false,
    // 访客模式地址
    VisitorsUrl: '',
    // 默认菜单
    useDefaultMenu: true,
    // 启用更新日志
    useUpdateLog: false,
    // 是否开启第三方免登
    enableThirdPartyLogin: false,
};
// 挂载外部配置文件
if ((window as any).Environment) {
    Object.assign(Environment, (window as any).Environment);
}