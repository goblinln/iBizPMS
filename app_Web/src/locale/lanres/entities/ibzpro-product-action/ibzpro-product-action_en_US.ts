import IBZProProductAction_en_US_Base from './ibzpro-product-action_en_US_base';

function getLocaleResource(){
    const IBZProProductAction_en_US_OwnData = {};
    const targetData = Object.assign(IBZProProductAction_en_US_Base(), IBZProProductAction_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;