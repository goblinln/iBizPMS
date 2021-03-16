import IBZProProduct_en_US_Base from './ibzpro-product_en_US_base';

function getLocaleResource(){
    const IBZProProduct_en_US_OwnData = {};
    const targetData = Object.assign(IBZProProduct_en_US_Base(), IBZProProduct_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;