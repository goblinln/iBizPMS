import AccountTestTask_en_US_Base from './account-test-task_en_US_base';

function getLocaleResource(){
    const AccountTestTask_en_US_OwnData = {};
    const targetData = Object.assign(AccountTestTask_en_US_Base(), AccountTestTask_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;