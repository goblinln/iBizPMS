import AccountTask_zh_CN_Base from './account-task_zh_CN_base';

function getLocaleResource(){
    const AccountTask_zh_CN_OwnData = {};
    const targetData = Object.assign(AccountTask_zh_CN_Base(), AccountTask_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;