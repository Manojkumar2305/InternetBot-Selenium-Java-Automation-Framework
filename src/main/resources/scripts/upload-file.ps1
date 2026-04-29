param (
    [Parameter(Mandatory = $true)]
    [string]$FilePath
)

Add-Type -AssemblyName System.Windows.Forms

$shell = New-Object -ComObject WScript.Shell

Start-Sleep -Milliseconds 800

if (-not $shell.AppActivate("Open")) {
    $shell.AppActivate("File Upload")
}

Start-Sleep -Milliseconds 500
[System.Windows.Forms.SendKeys]::SendWait($FilePath)
Start-Sleep -Milliseconds 300
[System.Windows.Forms.SendKeys]::SendWait("{ENTER}")
