import SysAccount_zh_CN_Base from './sys-account_zh_CN_base';

function getLocaleResource(){
    const SysAccount_zh_CN_OwnData = {};
    const targetData = Object.assign(SysAccount_zh_CN_Base(), SysAccount_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;