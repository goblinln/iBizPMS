import DynaFilter_en_US_Base from './dyna-filter_en_US_base';

function getLocaleResource(){
    const DynaFilter_en_US_OwnData = {};
    const targetData = Object.assign(DynaFilter_en_US_Base(), DynaFilter_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;