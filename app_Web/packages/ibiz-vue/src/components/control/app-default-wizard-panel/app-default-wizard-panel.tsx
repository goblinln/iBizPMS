import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { AppWizardPanelBase } from '../app-common-control/app-wizardpanel-base';
import './app-default-wizard-panel.less';

@Component({})
@VueLifeCycleProcessing()
export class AppDefaultWizardPanel extends AppWizardPanelBase { }