import AccountTask_en_US_Base from './account-task_en_US_base';

function getLocaleResource(){
    const AccountTask_en_US_OwnData = {};
    const targetData = Object.assign(AccountTask_en_US_Base(), AccountTask_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;