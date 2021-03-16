import IbzReportRoleConfig_zh_CN_Base from './ibz-report-role-config_zh_CN_base';

function getLocaleResource(){
    const IbzReportRoleConfig_zh_CN_OwnData = {};
    const targetData = Object.assign(IbzReportRoleConfig_zh_CN_Base(), IbzReportRoleConfig_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;