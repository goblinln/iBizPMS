/**
 * 用户自定义环境
 * 
 * @environment
 */
window.Environment = {
    // 应用基础路径
    BaseUrl: '',
    // 是否为开发模式
    devMode: true,
    // 是否为pc端应用
    isAppMode: false,
    // 是否开启权限认证
    enablePermissionValid: false,
    // 打开目标工具，可选参数：sln、mos
    debugOpenMode: 'mos',
    // 配置平台地址
    StudioUrl: 'http://172.16.170.145/mos/',
    // 中心标识
    SlnId: 'B4BF5C84-D020-4D9A-A986-8FA4FD72816C',
    // 系统标识
    SysId: 'B428B5BE-EA90-4101-A493-BA7085D89F0A',
    // 前端应用标识
    AppId: '6e0b7357169ef4eba84e1347ed94bd84',
    // 文件导出
    ExportFile: 'ibizutilpms/ztdownload',
    // 文件上传
    UploadFile: 'ibizutilpms/ztupload',
    // 是否开启访客模式
    VisitorsMode: true,
    // 启用更新日志
    useUpdateLog: true,
    // 访客模式地址
    VisitorsUrl: '/login/guest',
    // 是否开启第三方免登
    enableThirdPartyLogin: true,
};