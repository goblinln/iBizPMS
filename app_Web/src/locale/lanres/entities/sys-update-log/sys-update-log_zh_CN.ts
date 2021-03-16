import SysUpdateLog_zh_CN_Base from './sys-update-log_zh_CN_base';

function getLocaleResource(){
    const SysUpdateLog_zh_CN_OwnData = {};
    const targetData = Object.assign(SysUpdateLog_zh_CN_Base(), SysUpdateLog_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;