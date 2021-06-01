import Account_zh_CN_Base from './account_zh_CN_base';

function getLocaleResource(){
    const Account_zh_CN_OwnData = {};
    const targetData = Object.assign(Account_zh_CN_Base(), Account_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;