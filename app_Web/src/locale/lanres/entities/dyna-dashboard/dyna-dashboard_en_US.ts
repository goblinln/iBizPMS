import DynaDashboard_en_US_Base from './dyna-dashboard_en_US_base';

function getLocaleResource(){
    const DynaDashboard_en_US_OwnData = {};
    const targetData = Object.assign(DynaDashboard_en_US_Base(), DynaDashboard_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;