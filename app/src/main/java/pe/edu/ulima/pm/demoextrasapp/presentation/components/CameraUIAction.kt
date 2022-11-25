package pe.edu.ulima.pm.demoextrasapp.presentation.components

sealed class CameraUIAction {
    object OnCameraClick : CameraUIAction()
    object OnSwitchCameraClick : CameraUIAction()
}