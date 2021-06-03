import AccountBug_zh_CN_Base from './account-bug_zh_CN_base';

function getLocaleResource(){
    const AccountBug_zh_CN_OwnData = {};
    const targetData = Object.assign(AccountBug_zh_CN_Base(), AccountBug_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;