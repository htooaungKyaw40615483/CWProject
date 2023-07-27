---
name: 'Codecov Report Uploader Error: No Coverage Files Located'
about: 'While running the Codecov report uploader version 0.6.1 on the "CWProject"
  repository, the following error occurred:'
title: ''
labels: ''
assignees: ''

---

Codecov report uploader 0.6.1
[2023-07-27T18:47:42.957Z] ['info'] => Project root located at: /home/runner/work/CWProject/CWProject
[2023-07-27T18:47:42.958Z] ['info'] -> No token specified or token is empty
[2023-07-27T18:47:42.958Z] ['verbose'] Start of network processing...
[2023-07-27T18:47:42.958Z] ['verbose'] Searching for files in /home/runner/work/CWProject/CWProject
[2023-07-27T18:47:42.966Z] ['verbose'] coveragepy is not installed
[2023-07-27T18:47:42.966Z] ['info'] Searching for coverage files...
[2023-07-27T18:47:42.981Z] ['info'] Warning: Some files located via search were excluded from upload.
[2023-07-27T18:47:42.982Z] ['info'] If Codecov did not locate your files, please review https://docs.codecov.com/docs/supported-report-formats
[2023-07-27T18:47:42.983Z] ['verbose'] The error stack is: Error: No coverage files located, please try use -f, or change the project root with -R
at main (/snapshot/repo/dist/src/index.js)
[2023-07-27T18:47:42.982Z] ['error'] There was an error running the uploader: No coverage files located, please try use -f, or change the project root with -R


### Steps to Reproduce

1. Run the Codecov report uploader version 0.6.1 on the "CWProject" repository.

### Expected Behavior

The Codecov report uploader should locate and upload the coverage files without any errors.

### Actual Behavior

The uploader encountered an error indicating that no coverage files were located during the process.

### Additional Information

- **Repository:** https://github.com/username/CWProject

-**Screenshot** ![Error Encountered during Codecov Report Upload](https://github.com/htooaungKyaw40615483/CWProject/blob/master/Documents/Bug%20Screenshot/Error%20Encountered%20during%20Codecov%20Report%20Upload.PNG)

### Possible Solutions

To address the error, consider the following:
- Ensure that the appropriate token is specified for Codecov authentication (`-> No token specified or token is empty`).
- Review the coverage file search process and check for any potential issues (`coveragepy is not installed`).
- Check the project root and make sure it is correctly set for Codecov to locate the coverage files (`Project root located at: /home/runner/work/CWProject/CWProject`).

### Additional Context

The absence of coverage files may affect the accuracy of the code coverage analysis and reporting. Addressing this issue promptly will ensure accurate code coverage metrics for the project.

### Related Issues

None.
