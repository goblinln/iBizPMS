import AccountProduct_zh_CN_Base from './account-product_zh_CN_base';

function getLocaleResource(){
    const AccountProduct_zh_CN_OwnData = {};
    const targetData = Object.assign(AccountProduct_zh_CN_Base(), AccountProduct_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;