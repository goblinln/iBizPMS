import IbzReportRoleConfig_en_US_Base from './ibz-report-role-config_en_US_base';

function getLocaleResource(){
    const IbzReportRoleConfig_en_US_OwnData = {};
    const targetData = Object.assign(IbzReportRoleConfig_en_US_Base(), IbzReportRoleConfig_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;