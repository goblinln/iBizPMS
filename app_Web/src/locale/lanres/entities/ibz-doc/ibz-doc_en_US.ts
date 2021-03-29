import IBzDoc_en_US_Base from './ibz-doc_en_US_base';

function getLocaleResource(){
    const IBzDoc_en_US_OwnData = {};
    const targetData = Object.assign(IBzDoc_en_US_Base(), IBzDoc_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;