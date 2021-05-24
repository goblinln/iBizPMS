import IBZProProductHistory_zh_CN_Base from './ibzpro-product-history_zh_CN_base';

function getLocaleResource(){
    const IBZProProductHistory_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZProProductHistory_zh_CN_Base(), IBZProProductHistory_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;