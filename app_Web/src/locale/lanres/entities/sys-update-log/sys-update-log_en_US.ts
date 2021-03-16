import SysUpdateLog_en_US_Base from './sys-update-log_en_US_base';

function getLocaleResource(){
    const SysUpdateLog_en_US_OwnData = {};
    const targetData = Object.assign(SysUpdateLog_en_US_Base(), SysUpdateLog_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;