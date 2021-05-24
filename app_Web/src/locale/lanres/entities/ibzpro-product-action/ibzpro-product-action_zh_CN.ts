import IBZProProductAction_zh_CN_Base from './ibzpro-product-action_zh_CN_base';

function getLocaleResource(){
    const IBZProProductAction_zh_CN_OwnData = {};
    const targetData = Object.assign(IBZProProductAction_zh_CN_Base(), IBZProProductAction_zh_CN_OwnData);
    return targetData;
}

export default getLocaleResource;