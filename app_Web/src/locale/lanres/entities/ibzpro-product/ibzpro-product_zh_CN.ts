import IBZProProduct_zh_CN_Base from './ibzpro-product_zh_CN_base';

function getLocaleResource(){
    const IBZProProduct_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZProProduct_zh_CN_Base(), IBZProProduct_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;