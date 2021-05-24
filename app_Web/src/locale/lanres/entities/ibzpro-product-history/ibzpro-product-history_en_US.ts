import IBZProProductHistory_en_US_Base from './ibzpro-product-history_en_US_base';

function getLocaleResource(){
    const IBZProProductHistory_en_US_OwnData = {};
    const targetData = Object.assign(IBZProProductHistory_en_US_Base(), IBZProProductHistory_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;