import AccountTestCase_zh_CN_Base from './account-test-case_zh_CN_base';

function getLocaleResource(){
    const AccountTestCase_zh_CN_OwnData = {};
    const targetData = Object.assign(AccountTestCase_zh_CN_Base(), AccountTestCase_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;