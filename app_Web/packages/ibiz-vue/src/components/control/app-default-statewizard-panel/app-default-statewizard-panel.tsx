import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppStateWizardPanelBase } from '../app-common-control';
import './app-default-statewizard-panel.less';

@Component({})
@VueLifeCycleProcessing()
export class AppDefaultStateWizardPanel extends AppStateWizardPanelBase { }