import ProductTeam_en_US_Base from './product-team_en_US_base';

function getLocaleResource(){
    const ProductTeam_en_US_OwnData = {};
    const targetData = Object.assign(ProductTeam_en_US_Base(), ProductTeam_en_US_OwnData);
    return targetData;
}

export default getLocaleResource;