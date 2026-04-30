# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2026-04-30

### Added
- CLI command to convert NiFi JSON exports to YAML (`--convert`)
- Dry-run mode for `--convert` command to validate without outputting
- Unit tests for `convertNiFiJsonToYaml()` method (5 new tests)
- Documentation for NiFi JSON export format in README.md
- Automatic NiFi version detection (no more hardcoded versions)
- Support for all component types in JSON conversion (processors, connections, ports, funnels, process groups, remote process groups)

### Fixed
- Bundle detection for groovyx processors (use nifi-groovyx-nar instead of nifi-groovy-nar)
- Port name uniqueness (append group ID suffix to avoid "Public port name must be unique" error)
- GitHub release workflow permissions (use actions/github-script for release notes)

### Changed
- Updated GitHub Actions to v5 for Node.js 24 compatibility

## [Unreleased]

### Added
- Retry logic for NiFi API calls with configurable max retries and delay
- Dry-run mode for pipeline deployment (validate without deploying)
- Strongly-typed classes: `ProcessorConfig`, `ConnectionConfig`
- `AutoCloseable` support for `PipelineTester`
- Unit tests for bends support and input validation
- YAML schema documentation in README.md
- `version.properties` file for JAR naming
- Connection bends support (control points for connection lines)

### Fixed
- Groovy typo in `getBundleForProcessorType()` method
- `deleteProcessGroup()` now properly uses version parameter
- Input validation for `login()`, `deployPipeline()` methods
- GitHub workflow directory structure (workflows not workflow/s)
- Hardcoded NiFi version in bundle configuration (now auto-detected from running instance)

### Changed
- Updated GitHub Actions to v5 for Node.js 24 compatibility
- Updated `generate_release_notes` to `body` parameter in workflow

## [1.0.0-SNAPSHOT] - 2026-04-30

### Added
- Initial implementation of NiFi Pipeline Tester
- YAML to NiFi API conversion (`PipelineConverter`)
- Pipeline deployment (`PipelineTester`)
- Unit tests for converter
- Integration tests with TestContainers
- CLI support for deploying pipelines
- GitHub Actions workflow for CI/CD
- Javadoc documentation for main classes

[Unreleased]: https://github.com/anomalco/nifi-tester/compare/v1.0.0...HEAD
[1.0.0-SNAPSHOT]: https://github.com/anomalco/nifi-tester/releases/tag/v1.0.0
