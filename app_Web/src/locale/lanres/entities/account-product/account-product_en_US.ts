import AccountProduct_en_US_Base from './account-product_en_US_base';

function getLocaleResource(){
    const AccountProduct_en_US_OwnData = {};
    const targetData = Object.assign(AccountProduct_en_US_Base(), AccountProduct_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;