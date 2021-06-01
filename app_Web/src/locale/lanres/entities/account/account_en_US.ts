import Account_en_US_Base from './account_en_US_base';

function getLocaleResource(){
    const Account_en_US_OwnData = {};
    const targetData = Object.assign(Account_en_US_Base(), Account_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;