package pe.edu.ulima.pm.demoextrasapp.presentation.ui.modules.bibilioteca.components.camera

sealed class CameraUIAction {
    object OnCameraClick : CameraUIAction()
    object OnSwitchCameraClick : CameraUIAction()
}