import AccountTaskestimate_zh_CN_Base from './account-taskestimate_zh_CN_base';

function getLocaleResource(){
    const AccountTaskestimate_zh_CN_OwnData = {};
    const targetData = Object.assign(AccountTaskestimate_zh_CN_Base(), AccountTaskestimate_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;