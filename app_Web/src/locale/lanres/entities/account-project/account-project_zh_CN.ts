import AccountProject_zh_CN_Base from './account-project_zh_CN_base';

function getLocaleResource(){
    const AccountProject_zh_CN_OwnData = {};
    const targetData = Object.assign(AccountProject_zh_CN_Base(), AccountProject_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;