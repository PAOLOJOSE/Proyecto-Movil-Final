package pe.edu.ulima.pm.demoextrasapp.ui.modules.home.components

sealed class CameraUIAction {
    object OnCameraClick : CameraUIAction()
    object OnSwitchCameraClick : CameraUIAction()
}