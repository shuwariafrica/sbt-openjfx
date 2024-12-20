# Function to display help message
function Show-Help {
    Write-Output "Usage: .\tag-release.ps1 <version>"
    Write-Output ""
    Write-Output "Tags a git release with the specified version."
    Write-Output "The version must match the semantic versioning spec (e.g., 1.0.0, 1.0.0-alpha, 1.0.0+build123)."
    Write-Output "Ensures that the working directory is clean (no uncommitted changes or untracked files) before tagging."
    Write-Output ""
    Write-Output "Options:"
    Write-Output "  -h, --help    Show this help message and exit"
}

# Check if help flag is provided
if ($args[0] -eq "--help" -or $args[0] -eq "-h") {
    Show-Help
    exit 0
}

# Check if a version parameter is provided
if (-not $args[0]) {
    Write-Error "Error: Version parameter is required."
    Show-Help
    exit 1
}

# Validate the version parameter against the semantic versioning spec
$versionPattern = '^[0-9]+\.[0-9]+\.[0-9]+(-[0-9A-Za-z-]+(\.[0-9A-Za-z-]+)*)?(\+[0-9A-Za-z-]+(\.[0-9A-Za-z-]+)*)?$'
if ($args[0] -notmatch $versionPattern) {
    Write-Error "Error: Version must match the semantic versioning spec (e.g., 1.0.0, 1.0.0-alpha, 1.0.0+build123)."
    exit 1
}

# Check if the git status is clean
$gitStatus = git status --porcelain
if ($gitStatus) {
    Write-Error "Error: There are uncommitted changes or untracked files in the working directory."
    exit 1
}

# Compute the tag name
$TAG_NAME = "v$args[0]"

# Create an annotated and GPG-signed tag
if (git tag --sign --annotate $TAG_NAME -m "Release version $TAG_NAME") {
    Write-Output "Tag $TAG_NAME created with message: 'Release version $TAG_NAME'"
    exit 0
} else {
    Write-Error "Error: Failed to create tag $TAG_NAME"
    exit 1
}
