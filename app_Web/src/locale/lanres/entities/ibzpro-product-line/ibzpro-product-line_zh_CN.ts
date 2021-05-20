import IBZProProductLine_zh_CN_Base from './ibzpro-product-line_zh_CN_base';

function getLocaleResource(){
    const IBZProProductLine_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZProProductLine_zh_CN_Base(), IBZProProductLine_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;