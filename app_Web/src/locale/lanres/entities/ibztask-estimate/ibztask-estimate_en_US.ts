import IBZTaskEstimate_en_US_Base from './ibztask-estimate_en_US_base';

function getLocaleResource(){
    const IBZTaskEstimate_en_US_OwnData = {};
    const targetData = Object.assign(IBZTaskEstimate_en_US_Base(), IBZTaskEstimate_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;