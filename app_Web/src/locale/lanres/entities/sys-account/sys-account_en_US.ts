import SysAccount_en_US_Base from './sys-account_en_US_base';

function getLocaleResource(){
    const SysAccount_en_US_OwnData = {};
    const targetData = Object.assign(SysAccount_en_US_Base(), SysAccount_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;