import AccountTestTask_zh_CN_Base from './account-test-task_zh_CN_base';

function getLocaleResource(){
    const AccountTestTask_zh_CN_OwnData = {};
    const targetData = Object.assign(AccountTestTask_zh_CN_Base(), AccountTestTask_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;