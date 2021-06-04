import Reportly_en_US_Base from './reportly_en_US_base';

function getLocaleResource(){
    const Reportly_en_US_OwnData = {};
    const targetData = Object.assign(Reportly_en_US_Base(), Reportly_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;