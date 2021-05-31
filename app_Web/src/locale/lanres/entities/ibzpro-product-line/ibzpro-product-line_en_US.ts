import IBZProProductLine_en_US_Base from './ibzpro-product-line_en_US_base';

function getLocaleResource(){
    const IBZProProductLine_en_US_OwnData = {};
    const targetData = Object.assign(IBZProProductLine_en_US_Base(), IBZProProductLine_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;